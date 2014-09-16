(defproject strangeloop "0.1.0-SNAPSHOT"
  :description "Welcome to the wonderful world of SOUND"
  :url "https://github.com/josephwilk/strangeloop2014"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [overtone "0.9.1"]
                 [overtone.synths "0.1.0-SNAPSHOT"] ;;Some example synths to play with
                 [quil "2.2.2"]                     ;;Quil for visuals OR
                 [shadertone "0.2.3"]               ;; Shaders
                 [mud "0.1.0-SNAPSHOT"]])
