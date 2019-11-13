(ns programmer.core
  (:gen-class)
  (:require [generator.core]))

(defn to-number [num-str]
  (try
    (Integer. num-str)
    (catch Exception e 0)))


(defn parse_arguments
  "parse argument string to its corresponding type value"
  [args]
  (let [[operator-str start-str end-str] args]
    [operator-str (to-number start-str) (to-number end-str)]))

(defn -main
  "starting the programmer..."
  [& args]
  (if (< (count args) 3)
    (println "I do not know how to proceed without giving me any arguments yet, later I might change my code and patch myself, until then, \nHere, this is how you use me \n" "<programmer> arithmetic_operator start end\n")
    (println
    (-> args
        (parse_arguments)
        (generator.core/generate-code))))
)