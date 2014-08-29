(ns strangeloop.core
  "Come with us now through a journey through space and sound."
  (use
   [strangeloop.synths]
   [overtone.live]
   [mud.core]
   [mud.chords :as chords])
  (require [mud.timing :as time]
           [shadertone.tone :as tone]))

;;Manual triggering
(definst siney [amp 1 freq 300] (* amp (sin-osc:ar freq)))
(siney)
(ctl siney :amp 0.8 :freq 200)
(kill siney)

(defonce saw-buf (buffer 256))
(definst saw-deep [amp 1 buf saw-buf]
  (let [count (in:kr (:count time/beat-2th))
        note (buf-rd:kr 1 buf count)
        freq (midicps note)]
    (* amp
       (pitch-shift (bpf (lf-saw (* 0.5 freq)) 500) 0.4 1 0 0.01))))

(kill saw-deep)
(saw-deep)
(pattern! saw-buf (degrees [1 1 1 1 1 1 1 1
                            2 2 1 1 1 1 1 1
                            1 1 1 1 1 1 1 1
                            3 3 1 1 1 1 1 1] :major :F3))

;;Pattern based triggering
(defonce note-buf (buffer 256))
(definst siney [amp 1 buf note-buf]
  (let [count (in:kr (:count time/beat-2th))
        note (buf-rd:kr 1 buf count)
        freq (midicps note)]
    (* amp (bpf (pulse freq 0.9)))))

(siney)

;;(pattern! freq-buf [200 200 300 100 300 150 350])

(pattern! note-buf (degrees [1 1 1 1 1 1 1 1
                             2 2 2 2 6 6 6 6
                             4 4 4 4 5 5 5 5] :minor :F3))

(def siny-chords (chords/chord-synth siney 3))

(let [[c31 c32 c33 c34 c35 c36 c37] (chords-for :F3 :major 3)]
  (pattern! note-buf [c31 c31
                      c32 c32 c32 c32
                      c34 c34 c35 c35]))
;;set the rate
(ctl time/root-s :rate 6.)

(def wow-s (freesound-sample 238283))
(on-beat-trigger 12 #(do (wow-s)))
(remove-on-beat-trigger)
(remove-all-beat-triggers)

(defonce coin (freesound-sample 166184))
(defonce powerup (freesound-sample 153550))

(on-beat-trigger 128 #(do (coin)))
(on-beat-trigger 64  #(do (powerup)))

(defonce law (freesound-sample 112295))
(on-beat-trigger 256 #(do (law)))

(remove-on-beat-trigger)

(defonce kick-seq (buffer 256))
(def kick-s (freesound-sample 73560))
(def kickers (doall (map #(seqer :beat-num %1 :pattern kick-seq :num-steps 8 :amp 2.5 :buf kick-s :rate-start 0.2) (range 0 8))))
(pattern! kick-seq [1 0 0])

(defonce hat-seq (buffer 256))
(defonce hat-s (freesound-sample 73566))
(def hats (doall (map #(seqer :beat-num %1 :pattern hat-seq :num-steps 8 :amp 0.3 :buf hat-s :rate-start 0.9) (range 0 8))))
(pattern! hat-seq
          [1 1 0 0 0 1 0 0]
          [1 0 0 0 0 1 0 0]
          [1 0 0 0 0 1 0 0])
(kill seqer)

(comment
  (stop)
  (fadeout-master)
  (def beats (buffer->tap :beat-buf kick-seq))

  (tone/stop)
  (tone/start "resources/shaders/example.glsl"
              :textures [:overtone-audio :previous-frame]
              :user-data { "iBeat" (atom {:synth beats :tap "beat"})})

  )
