(ns user
  (:require [jayq.core :as jq :refer [$]])
  (:require-macros [jayq.macros :refer [let-ajax]]))


(let-ajax [a {:url      "http://localhost:8888/jsonrpc"
              :dataType :json
              :type     "POST"
              :data     {:jsonrpc 2.0
                         :id      6
                         :method  "get-serial-ports"
                         :params  []}}]
  (println a))

#_ (POST "http://localhost:8888/jsonrpc"
      {:body {:jsonrpc 2.0
              :id      6
              :method  "get-serial-ports"
              :params  []}
       :format :json
       :handler #(println %)
       :error-handler #(println %)})
