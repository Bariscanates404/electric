(ns app.P07b-PushMultipleInfoToTableByUsingMap
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            )
  )


(e/defn TableAppMultiByMap []
        (e/client
          (let [!db-map (atom {})
                !table-id (atom 1)
                !state (atom {:in          ""
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
                table-id (e/watch !table-id)]

            (defn table-id-counter [last-table-id]
              (swap! !table-id inc last-table-id)
              )
            (defn add-item-in-db-map [table-id username password]
              (swap! !db-map assoc-in [:person/id table-id]
                     {:person/id      table-id
                      :person/name    username
                      :person/surname password}
                     )
              )

            (ui/input in
                      (e/fn [v] (swap! !state assoc :in v))
                      (dom/props {:id    1 :placeholder (get (e/watch !state) :placeholder)
                                  :style {:background-color (get (e/watch !state) :bg-color)}})
                      (dom/on "keydown" (e/fn [enter]
                                              (when (= "Enter" (.-key enter))
                                                (when-some [givenValue (contrib.str/empty->nil (-> enter .-target .-value))]
                                                  (swap! !state assoc :username givenValue :in "")
                                                  ))))
                      (dom/on "keyup" (e/fn [keyup]
                                            (when-some [givenValue (contrib.str/empty->nil (-> keyup .-target .-value))]
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
              (e/fn [] ((table-id-counter table-id)
                        (add-item-in-db-map
                          table-id
                          (get (e/watch !state) :button1)
                          (get (e/watch !state) :button2)
                          )
                        (swap! !state assoc
                               :bg-color2 "inherit"
                               :bg-color "inherit"
                               :in ""
                               :in2 ""
                               :button1 nil
                               :button2 nil)
                        ))
              (dom/text "Submit!!!")
              (dom/props {:class "Button"
                          :style {:color            "white"
                                  :background-color "black"}}))

            (dom/table
              (dom/props {:class "hyperfiddle"})
              (dom/table
                (dom/tr
                  (dom/th (dom/text "Id"))
                  (dom/th (dom/text "Name"))
                  (dom/th (dom/text "Surname")))
                (e/for [{:keys [person/id person/name person/surname]} (vals (:person/id (e/watch !db-map)))]
                       (dom/tr
                         (dom/td (dom/text id))
                         (dom/td (dom/text name))
                         (dom/td (dom/text surname)
                                 )))))
            )
          )
        )


