(ns study.MantikAnaliziCalismalari.E15
  (:require [study.reusable-functions :as rf])
  (:use demo.core
        tupelo.core
        tupelo.test)
  )

;(mapv (juxt :id :name :surname) (for [len (range 1 (+ 1 (count my-map)))]
;                                  (my-map len)
;                                 ))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]





(defn tatilTracker [str]
  (if (= str "pazartesi")
    (println "haftaici"))
  (if (= str "sali")
    (println "haftaici"))
  (if (= str "cars")
    (println "haftaici"))
  (if (= str "persembe")
    (println "haftaici"))
  (if (= str "cuma")
    (println "haftaici"))
  (if (= str "cumartesi")
    (println "haftasonu"))
  (if (= str "pazar")
    (println "haftasonu"))
  )

(tatilTracker "pazar")
;=> haftasonu


;kullani kadın mı erkek mi soralım, kadın ise 55 yasından buyuk ise emekli olabilir degil ise emekli olamaz diyelim. erkek ise
;65 yaş diyelim.

(defn emeklilik-kontrolu [cinsiyet yas]
  (if (= cinsiyet "erkek")
    (if (> yas 65) "emekli olabilirsiniz beyefendi" "emekli olamazsınız beyefendi")
    (if (= cinsiyet "kadin")
      (if (> yas 55) "emekli olabilirsiniz hanimefendi" "emekli olamazsınız hanimefendi")
      (pr "yanlis bir cinsiyet girdiniz.")
      )
    )
  )

(emeklilik-kontrolu "erkek" 28)

(map
  (fn [x] (if (odd? x) (into [] cat x)))
  (range 0 10))
;=> [1 3  5 7 9]

(defn my-filter [p xs]
  (lazy-seq
    (when (seq xs)
      (let [head (first xs)
            tail (my-filter p (rest xs))]
        (if (p head) (cons head tail) tail)))))

(my-filter odd? (range 0 100))


(use 'clojure.walk)

(defn transform-maps-to-vector [map]
  (postwalk (fn [x]
              (if (odd? x) (into [] cat x) x))
            map)
  )

(transform-maps-to-vector 1)



(apply vector (for [x (range 10) :when (odd? x)] x))

