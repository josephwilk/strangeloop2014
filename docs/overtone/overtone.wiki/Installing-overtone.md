Installing Overtone is as simple as installing a standard Clojure library which consists of the following steps:

* Install leiningen
* Create a new project
* Pull in dependencies

### Install leiningen 2+

Head over here and follow the instructions: https://github.com/technomancy/leiningen/#installation

You should be able to type `lein` on your console and see a list of options.

### Create a new project

On your terminal, type the following:

```sh
$ lein new tutorial
```
This will create you a new project directory structure skeleton within which to place your music code.

### Pull in Dependencies

Overtone relies on a number of external dependencies which must be available on your system. Luckily this doesn't mean lots of manual downloading as lein will handle all this for you. All you need to do is to tell lein that your project depends on Overtone, and lein will figure out the rest. When you created the `tutorial` project, lein created a file called `tutorial/project.clj`. This is the file you use to tell lein about the specifics of your project. In this case we only care about telling lein that Overtone is a dependency. This is as simple as adding `[overtone "0.9.1"]` to the list of dependencies.

Therefore, open up `tutorial/project.clj` and edit it to look as follows:

```clj
(defproject tutorial "1.0"
  :dependencies [ [org.clojure/clojure "1.5.1"]
                  [overtone "0.9.1"] ])
```

Now, to pull in the dependencies, you just need to run `lein deps` from within the project directory:

```sh
$ cd tutorial
$ lein deps
```

### What next?

Familiarise yourself with the process of [[Starting a REPL]] and [[Connecting scsynth]] then head over to [[Getting Started]] to learn how to make some crazy sounds. If you want to run the latest and greatest development version of Overtone have look at [[Overtone on the Edge!]]
