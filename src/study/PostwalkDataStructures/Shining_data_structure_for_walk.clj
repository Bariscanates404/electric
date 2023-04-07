(ns study.PostwalkDataStructures.Shining-data-structure-for-walk)
;espanso


(->>
  [
   {
    :a    1
    :date 'created-at
    :b    {:c    2
           :date 'created-at}
    }
   {
    :f    1
    :date 'created-at
    :g    {:h    2
           :date 'created-at}
    }
   ]
  (clojure.walk/postwalk-replace {'created-at #inst "2000"}))