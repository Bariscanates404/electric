(ns app.P03-BmiCalculator
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            ))

(defn bmi [w h s] (/ (* s w) (* h h)))

(e/defn App []
        (let [!state (atom {
                            :in2         ""
                            :in3         ""
                            :button1     ""
                            :button2     ""
                            :button3     ""
                            :bg-color    "black"
                            :bg-color2   "black"
                            :bg-color3   "black"
                            :placeholder "..."})
              !result (atom "")
              in2 (get (e/watch !state) :in2)
              in3 (get (e/watch !state) :in3)]
          (e/client
            (dom/h1 (dom/text "BMI Index Calculator"))
            (dom/dt (dom/text "Gender"))
            (dom/dd
              (dom/select
                (dom/on "change" (e/fn [e] (swap! !state assoc :button1 (->
                                                                          e
                                                                          .-target
                                                                          .-value))
                                       (if
                                         (= (get (e/watch !state) :button1) "female")
                                         (swap! !state assoc :button1 "1")
                                         (swap! !state assoc :button1 "1.1"))
                                       ))
                (dom/option (dom/text "male"))
                (dom/option (dom/text "female")))
              )

            (dom/dt (dom/text "Weight (kg)"))
            (dom/dd
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
                                                    (swap! !state assoc :button2 givenValue)
                                                    ))))
                        (dom/on "keyup" (e/fn [keyup]
                                              (when-some [givenValue (contrib.str/empty->nil (->
                                                                                               keyup
                                                                                               .-target
                                                                                               .-value))]
                                                (swap! !state assoc :button2 givenValue)
                                                )))
                        ))

            (dom/dt (dom/text "Height (meter)"))
            (dom/dd
              (ui/input in3
                        (e/fn [v] (swap! !state assoc :in3 v))
                        (dom/props {:placeholder (get (e/watch !state) :placeholder)
                                    :style       {:background-color (get (e/watch !state) :bg-color3)}})
                        (dom/on "keydown" (e/fn [enter]
                                                (when (= "Enter" (.-key enter))
                                                  (when-some [givenValue (contrib.str/empty->nil (->
                                                                                                   enter
                                                                                                   .-target
                                                                                                   .-value))]
                                                    (swap! !state assoc :button3 givenValue)
                                                    ))))
                        (dom/on "keyup" (e/fn [keyup]
                                              (when-some [givenValue (contrib.str/empty->nil (->
                                                                                               keyup
                                                                                               .-target
                                                                                               .-value))]
                                                (swap! !state assoc :button3 givenValue)
                                                )))
                        ))
            )

          (ui/button
            (e/fn [] ((swap! !state assoc
                             :bg-color3 "inherit"
                             :bg-color2 "inherit"
                             :bg-color "inherit"
                             :in2 ""
                             :in3 "")
                      (reset! !result (bmi
                                        (-> (e/watch !state) :button2 parse-double)
                                        (-> (e/watch !state) :button3 parse-double)
                                        (-> (e/watch !state) :button1 parse-double))
                              )))
            (dom/text "Calculate!!!")
            (dom/props {:class "Button"
                        :style {:color            "white"
                                :background-color "black"}}))
          (dom/h3 (dom/text "Result is: " (e/watch !result)))
          ))

