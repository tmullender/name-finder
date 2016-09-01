(ns name-finder.filters)

(def length-of-name 4)

(def invalid-first-letters #{\T, \R, \J, \E, \C, \W, \X})

(def mandatory-letters #{\R, \A, \H, \J, \E, \L})

(defn filter-length 
  "Returns true if the length of the name matches length-of-name"
  [name]
  (= (count name) length-of-name))

(defn filter-first-letter 
  "Returns true if the first letter of the name is not in invalid-first-letters"
  [name]
  (not (contains? invalid-first-letters (first name))))

(defn contains-one-mandatory 
  "Returns true if the name contains one of the mandatory-letters"
  [name]
  (some mandatory-letters name))

(defn name-filter 
  "Combines the first letter, length and mandatory-letters filters"
  [name]
  (and (filter-first-letter name)
       (filter-length name)
       (contains-one-mandatory name)))
