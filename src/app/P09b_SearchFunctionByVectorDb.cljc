(ns app.P09b-SearchFunctionByVectorDb
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            [clojure.string :as str]
            ))

;---------------------------------------DEFN FUNCTIONS-----------------------------------
(defn filter-db [?s db]
  (->> db
       (filter (fn [[k v]] (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s)))))
       (into {})))

(defn add-item-in-db-vector [username password map]
  (swap! map conj {username password})
  )                                                         ;bu kod çalışıyor

(e/defn App []
        (e/client
          ;--------------------------------------STATEMENTS----------------------------------------
          (dom/h1 (dom/text "MAIN PAGE"))

          (let [!db-vector (atom {})
                !state (atom {
                              :in          ""
                              :in2         ""
                              :username    ""
                              :email       ""
                              :button1     ""
                              :button2     ""
                              :bg-color    "black"
                              :bg-color2   "black"
                              :placeholder "Please write your username..."})
                in (get (e/watch !state) :in)
                in2 (get (e/watch !state) :in2)
                !search (atom "")
                search (e/watch !search)
                filteredMap (filter-db search (e/watch !db-vector))]

            ;----------------------------------HTML/DOM ELEMENTS----------------------------------
            (ui/input in
                      (e/fn [v] (swap! !state assoc :in v))
                      (dom/props {:id          1
                                  :placeholder (get (e/watch !state) :placeholder)
                                  :style       {:background-color (get (e/watch !state) :bg-color)}})
                      (dom/on "keydown" (e/fn [enter]
                                              (when (= "Enter" (.-key enter))
                                                (when-some [givenValue (contrib.str/empty->nil (->
                                                                                                 enter
                                                                                                 .-target
                                                                                                 .-value))]
                                                  (swap! !state assoc :username givenValue :in "")
                                                  ))))
                      (dom/on "keyup" (e/fn [keyup]
                                            (when-some [givenValue (contrib.str/empty->nil (->
                                                                                             keyup
                                                                                             .-target
                                                                                             .-value))]
                                              (swap! !state assoc :button1 givenValue)
                                              )))
                      )

            (ui/input in2
                      (e/fn [v] (swap! !state assoc :in2 v))
                      (dom/props {:placeholder (get (e/watch !state) :placeholder)
                                  :style       {:background-color (get (e/watch !state) :bg-color2)}})
                      (dom/on "keydown" (e/fn [enter]
                                              (when (= "Enter" (.-key enter))
                                                (when-some [givenValue (contrib.str/empty->nil (->
                                                                                                 enter
                                                                                                 .-target
                                                                                                 .-value))]
                                                  (swap! !state assoc :email givenValue :in2 "")
                                                  ))))
                      (dom/on "keyup" (e/fn [keyup]
                                            (when-some [givenValue (contrib.str/empty->nil (->
                                                                                             keyup
                                                                                             .-target
                                                                                             .-value))]
                                              (swap! !state assoc :button2 givenValue)
                                              )))
                      )

            (ui/button
              (e/fn [] ((add-item-in-db-vector
                          (get (e/watch !state) :button1)
                          (get (e/watch !state) :button2)
                          !db-vector)
                        (swap! !state assoc
                               :bg-color2 "inherit"
                               :bg-color "inherit"
                               :in ""
                               :in2 ""
                               :button1 nil
                               :button2 nil)
                        ))
              (dom/text "Add Value!!!")
              (dom/props {:class "Button"
                          :style {:color            "white"
                                  :background-color "black"}}))

            ;----------------------------MAIN MAP ELEMENT----------------------------------
            (dom/table
              (dom/props {:class "hyperfiddle"})
              (dom/table
                (dom/tr
                  (dom/th (dom/text "Name"))
                  (dom/th (dom/text "Surname")))
                (e/for-by first [[k v] (e/watch !db-vector)]
                          (dom/tr
                            (dom/td (dom/text k))
                            (dom/td (dom/props {:style {:white-space :nowrap}}) (dom/text v))
                            ))
                )
              )



            ;----------------------------SEARCH TAB AND SEARCH TABLE----------------------------------
            (dom/h3 (dom/text "Search Tab"))

            (e/client                                       ;input elementine girilen degeri alır ve !search atomuna atama yapar.
              (ui/input search (e/fn [v] (reset! !search v))
                        (dom/props {:type "search"}))

              )

            (dom/table
              (dom/props {:class "hyperfiddle"})
              (dom/table
                (dom/tr
                  (dom/th (dom/text "Name"))
                  (dom/th (dom/text "Surname")))
                (e/for-by first [[k v] filteredMap]
                          (dom/tr
                            (dom/td (dom/text k))
                            (dom/td (dom/props {:style {:white-space :nowrap}}) (dom/text v))
                            ))
                )
              )
            )
          )
        )


