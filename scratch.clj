(ns user
  (:require [ajax.core :refer [GET POST]]))

#_ (let-ajax [a {:url      "http://localhost:8888/jsonrpc"
              :dataType :json
              :type     "POST"
              :data     {:jsonrpc 2.0
                         :id      6
                         :method  "get-serial-ports"
                         :params  []}}]
  (println a))

(POST "http://localhost:8888/jsonrpc"
      {:params        {:jsonrpc "2.0"
                       :id      6
                       :method  "get-serial-ports"
                       :params  []}
       :format        :json
       :handler       #(println %)
       :error-handler #(println %)})
