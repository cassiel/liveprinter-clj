(ns user
  (:require [ajax.core :refer [GET POST]]
            [clojure.core.async :as async :refer [<! >! go]])
  )

#_ (let-ajax [a {:url      "http://localhost:8888/jsonrpc"
              :dataType :json
              :type     "POST"
              :data     {:jsonrpc 2.0
                         :id      6
                         :method  "get-serial-ports"
                         :params  []}}]
  (println a))


(let [c (async/chan)]
  (POST "http://localhost:8888/jsonrpc"
        {:params        {:jsonrpc "2.0"
                         :id      6
                         :method  "get-serial-ports"
                         :params  []}
         :format        :json
         :handler       #(go (>! c (get-in % ["result" "ports"])))
         :error-handler #(println %)})
  (go (println (<! c)))
  )


(let [c (async/chan)]
  (POST "http://localhost:8888/jsonrpc"
        {:params        {:jsonrpc "2.0"
                         :id      5
                         :method  "set-serial-port"
                         :params  ["/dev/cu.usbmodem14101" 250000]}
         :format        :json
         :handler       #(go (>! c (get-in % ["result" 0 "messages"])))
         :error-handler #(println %)})
  (go (println (<! c))))

(let [c (async/chan)
      x (+ 30 (rand-int 160))
      y (+ 30 (rand-int 160))
      z (+ 4 (rand-int 116))
      ]
  (POST "http://localhost:8888/jsonrpc"
        {:params        {:jsonrpc "2.0"
                         :id      4
                         :method  "send-gcode"
                         :params [#_ "G1 X100 Y100 Z100 F8000"
                                  (format "G1 X%d Y%d Z%d F5000" x y z)]}
         :format        :json
         :handler       #(go (>! c %))
         :error-handler #(println %)})
  (POST "http://localhost:8888/jsonrpc"
        {:params        {:jsonrpc "2.0"
                         :id      4
                         :method  "send-gcode"
                         :params ["M400"]}
         :format        :json
         :handler       #(go (>! c %))
         :error-handler #(println %)})
  (go (println "ONE " (<! c))
      (println "TWO " (<! c))))
