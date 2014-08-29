(ns strangeloop.synths
  (:use overtone.live)
  (:require [mud.timing :as time]))

(defsynth buffer->tap [beat-buf 0 beat-bus 0 beat-size 16 measure 6]
  "Exposes some useful timing information which we can use in Shadertone"
  (let [cnt (in:kr beat-bus)
        beat (buf-rd:kr 1 beat-buf cnt)
        _  (tap "beat"          60 (a2k beat))
        _  (tap "beat-count"    60 (a2k (mod cnt beat-size)))
        _  (tap "measure-count"       60 (a2k (/ (mod cnt (* measure beat-size)) measure)))
        _  (tap "beat-total-count" 60 (a2k (mod cnt (* measure beat-size))))])
  (out 0 0))

(defsynth seqer
  "Plays a single channel audio buffer."
  [buf 0 rate 1 out-bus 0 beat-num 0 pattern 0  num-steps 8
   beat-bus (:count time/main-beat)     ;; Our beat count
   beat-trg-bus (:beat time/main-beat)  ;; Our beat trigger
   amp 0.7
   rate-start 0.1
   rate-limit 0.9]
  (let [cnt      (in:kr beat-bus)
        rander (mod cnt 1)
        beat-trg (in:kr beat-trg-bus)
        bar-trg  (and (buf-rd:kr 1 pattern cnt)
                      (= beat-num (mod cnt num-steps))
                      beat-trg)
        vol      (set-reset-ff bar-trg)]
    (out out-bus (* vol amp (scaled-play-buf :num-channels 1 :buf-num buf :rate (t-rand:kr rate-start rate-limit rander) :trigger bar-trg)))))
