First define some instruments:

```clj
(definst kick [freq 120 dur 0.3 width 0.5]
  (let [freq-env (* freq (env-gen (perc 0 (* 0.99 dur))))
        env (env-gen (perc 0.01 dur) 1 1 0 1 FREE)
        sqr (* (env-gen (perc 0 0.01)) (pulse (* 2 freq) width))
        src (sin-osc freq-env)
        drum (+ sqr (* env src))]
    (compander drum drum 0.2 1 0.1 0.01 0.01)))

;(kick)

(definst c-hat [amp 0.8 t 0.04]
  (let [env (env-gen (perc 0.001 t) 1 1 0 1 FREE)
        noise (white-noise)
        sqr (* (env-gen (perc 0.01 0.04)) (pulse 880 0.2))
        filt (bpf (+ sqr noise) 9000 0.5)]
    (* amp env filt)))

;(c-hat)
```

Then you can create a metronome with a specific BPM (beats per minute).  The ```(metronome <bpm>)``` returns a function that can be used to synchronize multiple instruments to the same rhythm.  The metronome remembers the time at which it was started and then when called with no arguments it will return the current beat count.  (i.e. # of beats since the start)  If called with one argument, a beat number, then a metronome function will return the absolute timestamp in milliseconds that that beat will occur.  This timestamp can be used to schedule events at a specific beat.

```clj
(def metro (metronome 128))

(metro) ; => current beat number
(metro 100) ; => timestamp of 100th beat
```

Now that we have everything ready, we can define a function that will recurse through time, each iteration triggering the next beat.  By passing the function to itself using the ```#'player``` type notation we are passing the var `player` rather than the current value of that var.  In this way the new value will be looked up every iteration, which allows us to continually redefine the function as it's playing.  Try commenting out the hi-hat line, or adjusting when it gets triggered, and then re-evaluate the function while it is still playing.

```clj
(defn player [beat]
  (at (metro beat) (kick))
  (at (metro (+ 0.5 beat)) (c-hat))
  (apply-by (metro (inc beat)) #'player (inc beat) []))

(player (metro))
```

Change the playback speed by sending a message to metro like this:

```clj
(metro-bpm metro 120)
```