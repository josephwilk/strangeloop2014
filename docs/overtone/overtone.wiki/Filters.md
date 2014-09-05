Filters take a signal and modify it in some manner.

## Linear filters

Overtone comes with a number of standard linear filters: `lpf`, `hpf`, and `bpf` are low-pass, high-pass and band-pass filters respectively.

```clojure
(demo 10 (lpf (saw 100) (mouse-x 40 5000 EXP)))
;; low-pass; move the mouse left and right to change the threshold frequency

(demo 10 (hpf (saw 100) (mouse-x 40 5000 EXP)))
;; high-pass; move the mouse left and right to change the threshold frequency

(demo 30 (bpf (saw 100) (mouse-x 40 5000 EXP) (mouse-y 0.01 1 LIN)))
;; band-pass; move mouse left/right to change threshold frequency; up/down to change bandwidth (top is narrowest)
```

## Nonlinear filters

You can do Karplus-Strong string synthesis with the `pluck` filter. Karplus-Strong works by taking a signal, filtering it and feeding it back into itself after a delay, so that the output eventually becomes periodic.

```clojure
;; here we generate a pulse of white noise, and pass it through a pluck filter
;; with a delay based on the given frequency
(let [freq 220]
   (demo (pluck (* (white-noise) (env-gen (perc 0.001 2) :action FREE)) 1 3 (/ 1 freq))))
```
    