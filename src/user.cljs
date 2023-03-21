(ns ^:dev/always user ; Electric currently needs to rebuild everything when any file changes. Will fix
  (:require
    app.P01-IncDec
    app.P03-BmiCalculator
    app.P04-TwoPageApp
    app.P02-PushInfoToTable
    app.P05-SliderApp
    app.todo-list
    app.P07-PushMultipleInfoToTable
    app.P06-TableAppByUsingVectorAndFor
    app.P06b-TableAppByUsingMapAndFor
    app.P07b-PushMultipleInfoToTableByUsingMap
    app.P07c-MapExample
    hyperfiddle.electric
    hyperfiddle.electric-dom2))

(def electric-main
  (hyperfiddle.electric/boot ; Electric macroexpansion - Clojure to signals compiler
    (binding [hyperfiddle.electric-dom2/node js/document.body]
      (app.P03-BmiCalculator/App.))))

(defonce reactor nil)

(defn ^:dev/after-load ^:export start! []
  (assert (nil? reactor) "reactor already running")
  (set! reactor (electric-main
                  #(js/console.log "Reactor success:" %)
                  #(js/console.error "Reactor failure:" %))))

(defn ^:dev/before-load stop! []
  (when reactor (reactor)) ; teardown
  (set! reactor nil))