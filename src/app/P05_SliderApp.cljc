(ns app.P05-SliderApp
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui4]))



(e/defn SliderApp []
        (e/client
          (let [!state (atom {:in "" :v 0 :v-state 0 :placeholder "Write a number please..."})]
            (let [in (get (e/watch !state) :in) v (get (e/watch !state) :v)]
              (dom/div (dom/props {:style {:display     :grid
                                           :width       "40em"
                                           :grid-gap    "0.5em"
                                           :align-items :center}})
                       (dom/h1 (dom/text "Slider  Example")
                               (dom/props {:style {:grid-row 1 :align-items :center
                                                   }}))

                       (ui4/range v (e/fn [newv] ((swap! !state assoc :v newv)))
                                  (dom/props {:min 0, :max 100, :style {:grid-row 2}}))


                       (ui4/input in (e/fn [v] (swap! !state assoc :in v))
                                  (dom/props {:placeholder (get (e/watch !state) :placeholder)
                                              :style       {:background-color (get (e/watch !state) :bg-color2)
                                                            :width            "47em" :height "1em" :align-items :center :grid-row 3}})
                                  (dom/on "keydown" (e/fn [enter]
                                                          (when (= "Enter" (.-key enter))
                                                            (when-some [givenValue (contrib.str/empty->nil (-> enter .-target .-value))]
                                                              (swap! !state assoc :v givenValue)
                                                              (set! (.-value dom/node)
                                                                    )))))
                                  (dom/on "keyup" (e/fn [keyup]
                                                        (when-some [givenValue (contrib.str/empty->nil (-> keyup .-target .-value))]
                                                          (swap! !state assoc :v-state givenValue)
                                                          )))
                                  )

                       (dom/button (dom/on "click" (e/fn [click] (swap! !state assoc :v (get (e/watch !state) :v-state))))
                                   (dom/text "Insert Num!!!")
                                   (dom/props {:style {:grid-row 4 :width "15em" :height "2em"
                                                       :grid-gap "10em" :align-items :auto
                                                       }})
                                   )

                       (dom/button (dom/on "click" (e/fn [click] (swap! !state assoc :v "")
                                                         (swap! !state assoc :in "")))
                                   (dom/text "Reset!!!")
                                   (dom/props {:style {:grid-row 4 :width "15em" :height "2em"
                                                       :grid-gap "1em" :align-items :auto
                                                       }})
                                   )

                       (dom/h1 (dom/p (dom/text "result is: " v))
                               (dom/props {:style {:grid-row 5 :align-items :center
                                                   }}))
                       )
              )
            )
          )
        )