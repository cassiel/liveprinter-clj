(ns user
  (:require [ajax.core :refer [GET POST]]))

(POST "http://localhost:8888/jsonrpc"
      {:body {:jsonrpc 2.0
              :id      6
              :method  "get-serial-ports"
              :params  []}
       :format :json
       :handler #(println %)
       :error-handler #(println %)})


(js/Date.)
