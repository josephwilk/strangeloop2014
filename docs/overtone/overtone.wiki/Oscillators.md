The oscillator ugens act as your source of raw audio content.  In subtractive synthesis, which we'll be focusing on here, you start by generating an audio signal with energy in the frequency bands you desire.  The typical wave forms come from the original analog synthesizers, where electronic circuits were used to generate periodic waveforms.  Here are some of the classic waveforms.  Experiment with different frequencies, attack, sustain and release settings too.  Many sounds will feel quite different depending on how you fade in and out of them.

(Note: If you are trying this out in a version of Overtone older than 0.9.0, you may have to use `lin-env` instead of `lin` in the following examples.)

```clojure
(definst sin-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4] 
  (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
     (sin-osc freq)
     vol))
(sin-wave)
```

```clojure
(definst saw-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4] 
  (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
     (saw freq)
     vol))

(definst square-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4] 
  (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
     (lf-pulse:ar freq)
     vol))

(definst noisey [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4] 
  (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
     (pink-noise) ; also have (white-noise) and others...
     vol))

(definst triangle-wave [freq 440 attack 0.01 sustain 0.1 release 0.4 vol 0.4] 
  (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
     (lf-tri freq)
     vol))
```

Note that you can also use these generators as control signals to modify parameters of other ugens.  Here an adjustable width pulse wave is shifting the frequency of the main oscillator:

```clojure
(definst spooky-house [freq 440 width 0.2 
                         attack 0.3 sustain 4 release 0.3 
                         vol 0.4] 
  (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
     (sin-osc (+ freq (* 20 (lf-pulse:kr 0.5 0 width))))
     vol))
```

In wavetable synthesis a single period waveform is stored in a buffer and used as a lookup table for the osc oscillator. 
