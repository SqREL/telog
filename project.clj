(defproject telog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [clj-http "3.9.1"]
                 [compojure "1.6.1"]
                 [org.immutant/web "2.1.10"]
                 [ring "1.7.0"]
                 [markdown-clj "1.0.2"]
                 [rum "0.11.2"]]
  :aliases {"newpost" ["run" "-m" "telog.generator/newpost"]}
  :profiles {
    :uberjar {
      :aot          [grumpy.server]
      :uberjar-name "grumpy.jar"
      :auto-clean   false
    }
    :dev {
      :dependencies [
        [org.clojure/tools.nrepl "0.2.13"]
        [cider/cider-nrepl "0.16.0"]
    ]}
  }
  :main telog.server)