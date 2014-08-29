(ns strangeloop.core
  (use [overtone.live]
       [mud.core]
       [mud.chords :as chords])
  (require [mud.timing :as time]))

;;Manual triggering
(definst siney [amp 1 freq 300] (* amp (sin-osc:ar freq)))
(siney)
(ctl siney :amp 0.5 :freq 300)
(kill siney)

;;Pattern based triggering
(defonce freq-buf (buffer 256))
(definst siney [amp 1 buf freq-buf]
  (let [count (in:kr (:count time/beat-4th))
        note (buf-rd:kr 1 buf count)
        freq (midicps note)]
    (* amp (sin-osc:ar freq))))

(siney)

;;(pattern! freq-buf [200 200 300 100 300 150 350])

(pattern! freq-buf (degrees [1 1 1 1 1 1 1 1
                             2 2 2 2 6 6 6 6
                             4 4 4 4 5 5 5 5] :minor :F3))

(def siny-chords (chords/chord-synth siney 3))

(let [[c31 c32 c33 c34 c35 c36 c37] (chords-for :F3 :minor 3)]
  (pattern! freq-buf [c31 c31 c31 c31
                      c32 c32 c32 c32
                      c34 c34 c35 c35]))
;;set the rate
(ctl time/root-s :rate 8.)

(defonce wow-s (freesound-sample 48183))
(on-beat-trigger 12 #(do (kick-drum-s)))
(remove-on-beat-trigger)

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

(defonce kick-seq (buffer 256))
(def kick-s (freesound-sample 171104))

(mono-player kick-s)
(doall (map #(seqer :beat-num %1 :pattern kick-seq :num-steps 8 :amp 1 :buf kick-s) (range 0 8)))
(pattern! kick-seq [1 0 0 0 1 0 0 0])
