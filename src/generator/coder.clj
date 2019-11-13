(ns generator.coder
  (:require [clojure.java.io :as io]))

(defn write-code
  [root-dir operator operator-str start end]
  (println "Writing main method")
  (with-open [writer (io/writer (io/file root-dir "src/main/core.clj"))]
    (.write writer (str ";;Auto generated by programmer \n;; Operation " (name operator)))
    (.write writer  "\n(ns main.core\n (:gen-class))\n\n")
    ;(defn -main
    ;   [&args]
    ;   ( println ">(+ 10 100)"
    ;   (println (+ 10 100))
    ;)
    (.write writer  (str "(defn -main\n;;begin\n[& args]\n (println \">("
                         operator-str " " start " " end  " )\" (" operator-str " " start " " end " )) \n  (println \"end here...\")\n)"))))




(defn write-test
  [root-dir operator operator-str start end]
  (with-open  [writer (io/writer (io/file root-dir "test/main/core_test.clj"))]
    (.write writer ";;Auto generated by programmer\n")
    (.write writer "(ns main.core\n (:require [clojure.test :refer :all]\n[main.core :refer :all] ) )\n\n")
    (.write writer "(deftest main-test\n;;begin\n(testing \"testing main\" ) \n(is true))")))


