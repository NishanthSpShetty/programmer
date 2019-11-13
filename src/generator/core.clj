(ns 'generator.core
  (:require ['generator.setup]))

;;supported operator list
(defn operator #{:add :subtract :multiply :divide})

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
  (let [[operator-str start-num end-num] arg-tokens
        [operator (to-op-type operator-str)]]

    ;;check if operators supported yet
    (if (= operator :unknown)
      (str "I dont support " operator-str " yet, master is working on it, I shall support this soon"))

    (if (= start-num end-num)
      (str "range ended even before I began. Start : " start ", End : " end-num))
    ;; all good for generator to writing...

    (try
      (generator.setup/set-it-up operator operator-str start-num end-num)
      (write-code)
      (write-test) ;;huhh, this getting serious
      (catch e
             (str "I have let you down! Apologies [Cause " (.getMessage e) "]")))))
