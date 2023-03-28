(ns study.VeriAnalizProblemleri.D07
  (:require [study.reusable-functions :as rf]))




;Bu problemde bir girdi veri var, bir de bir arama kriteri var.
;
;Girdi:
;
;```clojure
;[1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}]
;```
;
;Arama kriteri:
;
;```
;"ca"
;```
;
;Bu anahtar kelimenin geçtiği `surname` property'sine sahip objeleri bulun.