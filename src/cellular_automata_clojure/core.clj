(ns cellular-automata-clojure.core)

(defn -main [& args]
  (map #(if (string? %) (read-string %) %) args))
