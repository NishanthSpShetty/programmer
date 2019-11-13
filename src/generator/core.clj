(ns generator.core
  (:require [generator.setup]
            [generator.coder]))

;;supported operator list
(def operator #{:add :subtract :multiply :divide})

(defn to-op-type
  [op-str]
  (case op-str
    "+" :add
    "-" :subtract
    "*" :multiply
    "/" :divide
 ;   "%" :mod
    :unknown))


(defn generate-code
  "I will generate code!!!"
  [arg-tokens]
  (let
   [[operator-str start-num end-num] arg-tokens
    operator (to-op-type operator-str)]
    (println operator-str " " start-num " " end-num)
    ;;check if operators supported yet
    (cond (= operator :unknown)
          (str "I dont support " operator-str " yet, master is working on it, I shall support this soon"))

    (cond (= start-num end-num)
          (str "range ended even before I began. Start : " start-num ", End : " end-num))
    ;; all good for generator to writing...

    (try
      (println "starting generator ")
      (let [root-dir
            (generator.setup/set-it-up operator operator-str start-num end-num)]
        (println "Generating code...")
        (generator.coder/write-code root-dir operator  operator-str start-num end-num)
        (println "Generating tests...")
        (generator.coder/write-test root-dir operator  operator-str start-num end-num)
        (str "programmer ran Successfully , check projects `" root-dir "` and read README to know how to use."))

      (catch Exception e
        (throw e)
        (str "I have let you down! Apologies [Cause " (.getMessage e) "]")))))
