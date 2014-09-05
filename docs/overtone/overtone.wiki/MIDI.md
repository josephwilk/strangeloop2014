## Using the event stream

Overtone 0.7.1 automatically detects all connected MIDI devices on boot and registers the appropriate handlers for you. To see a list of MIDI devices detected by Overtone, use:

```clj
(midi-connected-devices)
```

The MIDI device should be connected and powered on before starting Overtone. When you bash the keys on the keyboard, Overtone receives internal events in its event stream. To see them use:

```clj
(event-debug-on)
```

To stop:

```clj
(event-debug-off)
```

You should see that for each key press, there are two events. A general midi control change event:

```clj
[:midi :note-on]
```

and a device-specific event i.e.:

```clj
[:midi-device Evolution Electronics Ltd. Keystation 61e Keystation 61e :note-on]
```

For simplicity use the general event type:

```clj
(on-event [:midi :note-on]
          (fn [e]
            (let [note (:note e)
                  vel  (:velocity e)]
              (your-instr note vel)))
          ::keyboard-handler)
```

The last argument is a keyword which can be used to refer to this handler, so you can later do:

```clj
(remove-event-handler ::keyboard-handler)
```

## Simple Midi Keyboard Control

Use `midi-poly-player` for simple control of Overtone instruments.

Define an inst to play with the midi keyboard

```clj
(definst steel-drum [note 60 amp 0.8]
  (let [freq (midicps note)]
    (* amp
       (env-gen (perc 0.01 0.2) 1 1 0 1 :action FREE)
       (+ (sin-osc (/ freq 2))
          (rlpf (saw freq) (* 1.1 freq) 0.4)))))
```

Define a player that connects midi input to that instrument.

```clj
(def player (midi-poly-player steel-drum))
```

When you want to stop or change sounds, use `midi-player-stop`.

```clj
(midi-player-stop)
```