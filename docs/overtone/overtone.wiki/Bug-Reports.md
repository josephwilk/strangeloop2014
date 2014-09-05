Sometimes things burn and crash or just don't do what you expect. It's the way of the world. However, things don't always have to be that way! If you find you have issues with Overtone then please file a GitHub Issue so that we can look into it and hopefully stop it from happening again.

https://github.com/overtone/overtone/issues?sort=created&direction=desc&state=open

### What to Include

The information you give us can be crucial to helping us duplicate the bug on our machines. Essentially, we'd like a description of the "shortest path" from using `overtone.live` or `overtone.core` to seeing the crash. Sometimes the design of a synth may cause a crash - if you believe that's the case, be sure to include that. The shorter the description the better.  Something like: 

Boot the internal server:

```clj
(use 'overtone.live)
```

Define the following synth:

```clj
(definst foo  [] ( "insert bad synth design here" )
```

Trigger foo:

```clj
(foo)
```

and BOOM! 

### External Devices

If your error occurs whilst using external devices through an OSC/MIDI interface, try to reproduce the error without the external device or consider sending bug reports to the appropriate library's GitHub Issue page:

* https://github.com/overtone/osc-clj
* https://github.com/overtone/midi-clj

### Environment Information
This extra information may be useful with particularly nasty bugs:

* Operating System
* CPU Architecture
* Server type (internal/external)
* Server version (if external)
* JVM version 