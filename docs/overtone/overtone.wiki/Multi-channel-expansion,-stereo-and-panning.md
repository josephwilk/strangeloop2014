Most audio ugens take a single channel input and produce a single channel of output.  So for example, here is a monophonic sin-wave oscillator:

```clojure
(defsynth sin1 [freq 440]
  (out 0 (sin-osc freq)))
```

When played you will only hear this synth on the left speaker, because it only has a single channel.  One way to create a stereo version would be to use two oscillators, sending them to buses one and two (left and right).

```clojure
(defsynth sin2 [freq 440]
  (out 0 (sin-osc freq))
  (out 1 (sin-osc freq)))
```

Sometimes for the purposes of synthesizing more interesting sounds you will want to layer multiple waveforms.  For example, lets add a square wave one octave below these sin waves.  (Since oscillator ugens output waveforms with an amplitude between -1 and 1, and output ugens expect signals with this same range, we need to multiply by 0.5 after adding the two waveforms):

```clojure
(defsynth sin-square [freq 440] 
  (out 0 (* 0.5 (+ (square (* 0.5 freq)) (sin-osc freq))))
  (out 1 (* 0.5 (+ (square (* 0.5 freq)) (sin-osc freq)))))
```

As you can see it gets cumbersome fast working this way, so we can do a few things to simplify our synth definition.  SClang introduced the idea of multi-channel expansion, which we have adapted to Overtone using macros.  With multi-channel expansion ugens that are designed to process a single channel of input will automatically expand to process multiple channels when a collection is passed where a single argument value is expected.  For example, to generate two sin waves we can do this:

```clojure
(sin-osc [440 443])
```

which will return a seq of two oscillators.  If this seq is passed on to another ugen, like a filter, then it will also expand and return two filters:

```clojure
(lpf (sin-osc [440 443]) 600)
```

So our stereo sin wave synth from above can be rewritten like this:

```clojure
(defsynth sin-square2 [freq 440] 
  (out 0 (* [0.5 0.5] (+ (square (* 0.5 freq)) (sin-osc freq)))))
```
or like this:

```clojure
(defsynth sin-square2 [freq 440] 
  (out 0 (* 0.5 [(square (* 0.5 freq)) (sin-osc freq)])))
```

which will play the square in the left channel and the sin on the right.