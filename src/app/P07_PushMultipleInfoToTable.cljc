(ns app.P07-PushMultipleInfoToTable
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            ))

(e/defn TableAppMulti []
        (e/client
          (let [!db-vector (atom []) !state (atom {:in "" :in2 "" :username "" :email "" :button1 "" :button2 "" :bg-color "black" :bg-color2 "black" :placeholder "Please write your username..."})]
            (let [in (get (e/watch !state) :in) in2 (get (e/watch !state) :in2)]
              (defn add-item-in-db-vector [username password]
                (swap! !db-vector conj {:username username :email password}))

              (ui/input in (e/fn [v] (swap! !state assoc :in v))
                        (dom/props {:id 1 :placeholder (get (e/watch !state) :placeholder) :style {:background-color (get (e/watch !state) :bg-color)}})
                        (dom/on "keydown" (e/fn [enter]
                                                (when (= "Enter" (.-key enter))
                                                  (when-some [givenValue (contrib.str/empty->nil (-> enter .-target .-value))]
                                                    (swap! !state assoc :username givenValue)
                                                    (set! (.-value dom/node))
                                                    (swap! !state assoc :in "")
                                                    ))))
                        (dom/on "keyup" (e/fn [keyup]
                                              (when-some [givenValue (contrib.str/empty->nil (-> keyup .-target .-value))]
                                                (swap! !state assoc :button1 givenValue)
                                                )))
                        )

              (ui/input in2 (e/fn [v] (swap! !state assoc :in2 v))
                        (dom/props {:placeholder (get (e/watch !state) :placeholder) :style {:background-color (get (e/watch !state) :bg-color2)}})
                        (dom/on "keydown" (e/fn [enter]
                                                (when (= "Enter" (.-key enter))
                                                  (when-some [givenValue (contrib.str/empty->nil (-> enter .-target .-value))]
                                                    (swap! !state assoc :email givenValue)
                                                    (set! (.-value dom/node))
                                                    (swap! !state assoc :in2 "")
                                                    ))))
                        (dom/on "keyup" (e/fn [keyup]
                                              (when-some [givenValue (contrib.str/empty->nil (-> keyup .-target .-value))]
                                                (swap! !state assoc :button2 givenValue)
                                                )))
                        )

              (ui/button
                (e/fn [] ((add-item-in-db-vector (get (e/watch !state) :button1) (get (e/watch !state) :button2))
                          (swap! !state assoc :bg-color2 "inherit")
                          (swap! !state assoc :bg-color "inherit")
                          (swap! !state assoc :in "")
                          (swap! !state assoc :in2 "")))
                (dom/text "Submit!!!")
                (dom/props {:class "Button" :style {:color "white" :background-color "black"}}))

              (dom/table
                (dom/props {:class "hyperfiddle"})
                (e/client
                  (dom/table
                    (dom/tr
                      (dom/th (dom/text "Username"))
                      (dom/th (dom/text "Email")))
                    (e/for [{:keys [username email]} (e/watch !db-vector)]
                           (dom/tr
                             (dom/td (dom/text username))
                             (dom/td (dom/text email)))
                           )
                    )
                  )
                )
              )
            )
          )
        )