## Synth Design Issues

If you're having trouble with a particular synth design, it's possible that there are bugs in the way Overtone generates the synthdef. This is a process we're continuing to iron out but is still probably the most fragile part of Overtone. Typical issues are:

* The synth doesn't play
* Playing the synth crashes sc-server and the JVM - BOOM!
* The synth doesn't do what you expect

In each of these cases, the easiest way to verify your synth design is to translate it to sclang and execute it there. If it doesn't play/does a similarly unexpected thing, chances are your synth design is off. If, however, your synth plays as expected, chances are there is a bug in Overtone. In this case we'd really appreciate you submit a bug request providing the Overtone synth that breaks and, if possible, an sclang synth that works. It is also very helpful if you can reduce the synth design down to just the elements that break - the simpler your synth design, the easier it will be for us to figure out what's going wrong.

A typical issue we face is that our ugen metadata is off, which can cause all manner of issues. If you suspect a particular ugen isn't working with your synth, then a great way of verifying this is to look up the SuperCollider documentation for that ugen and translate one of the examples into Overtone and see if things still break. Here's an example translation of one of the examples in the `LocalOut` ugen doc:

```supercollider
    //SuperCollider version
    {
            var source, local;
     
            source = Decay.ar(Impulse.ar(0.3), 0.1) * WhiteNoise.ar(0.2);
     
            local = LocalIn.ar(2) + [source, 0]; // read feedback, add to source
     
            local = DelayN.ar(local, 0.2, 0.2); // delay sound
     
            // reverse channels to give ping pong effect, apply decay factor
            LocalOut.ar(local.reverse * 0.8);
     
            Out.ar(0, local);
    }.play;
```

```clojure
    ;;Overtone version    
    (defsynth foo
      (let [src (* (decay (impulse 0.3) 0.1) (white-noise 0.2))
        local (+ (local-in 2) [src 0])
        local (delay-n local 0.2 0.2)
        _ (local-out (* (reverse local) 0.8))]
      (out 0 local)))
```

## Comparing Synthdefs

If your Overtone implementation of your synth design doesn't behave similarly to the sclang version, then in order to work out what the differences are, you need to compare the synthdefs produced from both systems. A synthdef is a binary version of the synth design and is the format that is understood by scserver. Luckily you don't have to examine the binary format directly as Overtone can generate a human readable version for you. It is this representation that we can use to compare both synthdefs to see where they differ.


## Generating sclang synthdefs

* Open up SuperCollider
* Create a new buffer
* Code up your synthdef i.e.

```supercollider
     SynthDef("help-Diskout", {arg bufnum;
            DiskOut.ar(bufnum, In.ar(0,2));
     })
```

* call store on it! You can do this directly:

```supercollider
     SynthDef("help-Diskout", {arg bufnum;
            DiskOut.ar(bufnum, In.ar(0,2));
     }).store;
```

* Locate your file. On 
  - OS X: ~/Library/Application Support/SuperCollider/synthdefs/help-Diskout.scsyndef
  - Linux: ~/.local/share/SuperCollider/synthdefs/help-Diskout.scsyndef
  - Windows: %LOCALAPPDATA%\SuperCollider\synthdefs\help-Diskout.scsyndef
* Read and bind the synthdef: 

```clojure
(use '[overtone.sc.machinery.synthdef :only [synthdef-read]]) 
(def sc-synthdef (synthdef-read "/path/to/synthef.scsyndef"))
```

## Generating Overtone synthdefs

* Fire up Overtone
* Define your synth/inst i.e.

```clojure
    (defsynth help-diskout [bufnum 0]
      (disk-out bufnum (in 0 2)))
```
    
* Don't trigger it!
* Read and bind the synthdef `(def o-synthdef (:sdef help-diskout))`

## Comparing synthdefs

Currently the best way of doing this is to pretty print both synthdefs and place them in side-by-side buffers for manual comparison. Pick your way through each one and see where they differ.

## Miscellaneous Questions

**Q: Is there an equivalent to sclang's .range() syntax?**

**A: No** To translate something like this:
```supercollider
    SinOsc.ar(3).range(30,40)
```
the current way to achieve your goals in Overtone is to do something like:
```clojure
    (lin-lin (sin-osc 3) -1 1 30 40)
```
Which linearly maps the input range [-1,1] to [30,40].