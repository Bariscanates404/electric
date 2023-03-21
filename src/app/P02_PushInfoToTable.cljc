(ns app.P02-PushInfoToTable
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            ))


(e/defn TableApp []
        (e/client
          (let [!state (atom {
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
                in2 (get (e/watch !state) :in2)]

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
              (e/fn [] ((swap! !state assoc
                               :username (get (e/watch !state) :button1)
                               :email (get (e/watch !state) :button2)
                               :bg-color2 "inherit"
                               :bg-color "inherit"
                               :in ""
                               :in2 "")))
              (dom/text "Submit!!!")
              (dom/props {:class "Button"
                          :style {:color            "white"
                                  :background-color "black"}}))

            (dom/table
              (dom/props {:class "hyperfiddle"})
              (e/client
                (dom/tr
                  (dom/th (dom/text (str "Username")))
                  (dom/th (dom/text (str "Email")))
                  )
                (dom/tr
                  (dom/td (dom/text (get (e/watch !state) :username)))
                  (dom/td (dom/text (get (e/watch !state) :email)))
                  )
                )
              )
            )
          )
        )
