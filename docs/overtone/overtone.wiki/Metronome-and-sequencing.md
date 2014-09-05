Here's an example of how to set up a simple metronome sound, for musical practice. Note that once you define a metronome (one-twenty-bpm in the example below), it will start counting beats.

```clj
; setup a sound for our metronome to use
(def kick (sample (freesound-path 2086)))

; setup a tempo for our metronome to use
(def one-twenty-bpm (metronome 120))

; this function will play our sound at whatever tempo we've set our metronome to 
(defn looper [nome sound]    
    (let [beat (nome)]
        (at (nome beat) (sound))
        (apply-by (nome (inc beat)) looper nome sound [])))

; turn on the metronome
(looper one-twenty-bpm kick)
(stop)

; to get a feel for how the metronome works, try defining one at the REPL
(def nome (metronome 200))
(nome)
; 8 
; why is this 8? shouldn't it be 1? let's try it again
(nome)
;140
; whoah, it's almost like it's ticking in the background. 
; it is, in fact, ticking in the background. a "beat" is just convenient way to represent a timestamp.
; leave your metronome defined at the REPL, and the beat number will steadily increase, even if you aren't
; using the object for anything.
```

