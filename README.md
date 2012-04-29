# clj-postageapp

A Clojure Wrapper for the [postageapp](http://postageapp.com) API

## CI Status
[![Build Status](https://secure.travis-ci.org/smnirven/clj-postageapp.png)](http://travis-ci.org/smnirven/clj-postageapp)

## Usage

With leiningen, include it by adding this to your project.clj file:
```clojure
  :dependencies [[clj-postageapp "0.2.0-alpha"]]
```

The main functionality is available through the ```clojure clj-postageapp.mailman ``` namespace

```clojure
  (require '[clj-postageapp.mailman :as mail]
```

Before using the library, be sure to init it with your postageapp API
key

```clojure
  (mail/init! "YOUR API KEY")
```

Currently the library supports two postageapp API requests,
get-account-info, and send-message

```clojure
  (mail/send-message {:recipients ["test@test.com"] :subject
  "Whazzzup" :from "no-reply@test.com" :template "the_postageapp_template_to_use"})
```

## Version History

### 0.2.0-alpha -- April 29, 2012
* Support for custom template variables in send_message API call

### 0.1.0-alpha -- April 27, 2012
* First release, support for get_account_info and send_message API
calls

## License

Copyright (C) 2012 Thomas Steffes

Distributed under the Eclipse Public License, the same as Clojure.
