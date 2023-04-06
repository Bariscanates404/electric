(ns study.MantikAnaliziCalismalari.YunusEmrePractice)



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

(defn filter-odd
  [vals]
  (reduce
    (fn [cum item]                                          ;function
      (if (odd? item)
        (conj cum item)                                     ; if odd, append to cum result
        cum                                                 ; if even, leave cum result unchanged
        ))
    []                                                      ; init value for `cum`
    vals                                                    ; sequence to reduce over
    ))


(apply vector (for [x (range 10) :when (odd? x)] x))
(filter-odd '(10))
