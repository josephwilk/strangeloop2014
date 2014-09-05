At this point we assume that you have [[installed Overtone|Installing Overtone]] and [[started a REPL|Starting a REPL]]. You should be in the position where you see the following on your terminal:

    user=>

Clojure is sitting there waiting for your next command, silent and obediently. What shall we ask of it? Well, if we wish it to make our speakers sing out the glorious swelling flush of powerful angelic wings rushing by, we need to load up an Overtone environment and connect to an scsynth server. So, let's get cracking. However, before we get started, we need a quick chat about servers.


### The Difference Between External & Internal Servers

Overtone supports both internal and external instances of the SuperCollider server (fondly referred to as scsynth - for SuperCollider Synth). The internal server is good for quick setup (there are no external dependencies to install and allows fast access to server buffers for transferring sound data and using the scopes). The external server requires a separate installation of SuperCollider itself but is more robust in that crashes in the server (through malformed synth designs etc.) don't also crash the JVM (which is the case for the internal server). 

Note - the internal server is not currently supported for all architecture/operating system combinations. However, the external server should work everywhere.

Let's summarise that:

* Internal Server
  - Pros - Enables using the scope to visualise audio and buffers. Fast retrieval of audio buffers. Super simple to use.
  - Cons - Crashes whole JVM when scsynth crashes, doesn't work everywhere and doesn't allow for multiple clients to connect to the same server (although you can coordinate external systems through Overtone itself)
* External Server
  - Pros - More robust setup - works everywhere.
  - Cons  - No scope or fast audio buffer retrieval (although you still can access buffer data much more slowly)

### OS-specific setup

* __Linux__ - [[Installing and Starting Jack]]
* __Windows__ - No extra steps necessary.
* __OS X__ - No extra steps necessary.

### Connecting to an Internal Server

This is super easy. We simply issue the following statement to our Clojure REPL:

    user=>(use 'overtone.live)

NB if you're using OSX + lein and get `java.lang.Exception: Too many arguments to def (defaults.clj:4)` try specifying Clojure 1.3.0 instead of 1.2.1 and removing the old Clojure jar from the lib folder.

Clojure will pull in all of Overtone's musical goodness, then diligently fire up an internal server process and connect to it for us. When the prompt returns, we're ready to go.

### Connecting to an External Server

In order to connect to an external server we need to do two separate steps. First we need to tell Clojure to load up an Overtone environment and once we've done that we need to connect to an external server. To load up Overtone in the REPL issue:

    user=>(use 'overtone.core)

We now have access to all of Overtone's functionality, only most of it is useless without a synthesis engine to actually make sound. To connect to one we need to either connect to an already running server or we need to boot one ourselves and then connect to it. Overtone makes both of these options simple, however you need to ensure you've already manually installed SuperCollider. If not, head here: http://supercollider.sourceforge.net/ and install a version for your architecture. 

**If you're running Linux, make sure that the `scsynth` executable is in your `PATH`.**

As mentioned, before you connect you need to choose whether you want to boot a new server or connect to an existing server.

#### Explicitly boot a server from within Overtone

* call `(boot-external-server)`

#### Connect to a currently running server

* make sure your server is running. (If you're using the standard SuperCollider distribution this will mean booting the 'localhost' server). 
* find out which port the server is listening on (this is typically `57110` for the SuperCollider localhost server)
* call `(connect-external-server 57110)` (replacing the port with the specific port you wish to use.)
* If the server is on a different machine use `(connect-external-server "192.168.1.23" 57110)` substituting the appropriate hostname and port number.

### What next?

Head over to [[Getting Started]] to learn how to make some crazy sounds.