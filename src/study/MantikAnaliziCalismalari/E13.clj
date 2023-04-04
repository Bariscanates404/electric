(ns study.MantikAnaliziCalismalari.E13)

(comment
  ; q: destructuring işlemi debug edilebilir mi?
  (defn pred [[_ {:keys [id name surname] :as v}]]
    (str/includes? (str/lower-case v)
                   (str/lower-case substring)))

  (pred (first coll))
  (first coll)
  ;=> [1 {:id 1, :name "ali", :surname "veli"}]
  (let
    [[_ {:keys [id name surname] :as v}] (first coll)]
    (str "id: " id " name: " name))
  ;=> "id: 1 name: ali"

  (let
    [[_ {:keys [id name surname] :as v}] [1 {:id 1, :name "ali", :surname "veli"}]]
    (str "id: " id " name: " name))

  ; end
  ,)
