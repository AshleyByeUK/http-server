[![Build Status](https://travis-ci.org/AshleyByeUK/http-server.svg?branch=master)](https://travis-ci.org/AshleyByeUK/http-server)
[![codecov](https://codecov.io/gh/AshleyByeUK/http-server/branch/master/graph/badge.svg)](https://codecov.io/gh/AshleyByeUK/http-server)
[![Maintainability](https://api.codeclimate.com/v1/badges/ad7ff723d172b8b0eb36/maintainability)](https://codeclimate.com/github/AshleyByeUK/http-server/maintainability)

# http-server

An HTTP server implementation in Java, which conforms to [RFC7230](https://tools.ietf.org/html/rfc7230) for
implemented features.

## Obtaining the source

To obtain the source code, clone the git repository:

```
git clone git@github.com:AshleyByeUK/http-server.git
```

## Running the server

## Testing

### Unit tests

Unit tests can be executed with Gradle:

```
./gradlew test
```

Test coverage reports can be generated with Gradle:

```
./gradlew test jacocoTestReport
```

The generated reports can be viewed in the browser:

```
open build/reports/jacoco/test/html/index.html
```

### Acceptance tests

Acceptance tests require [Bundler](https://bundler.io/) to be installed. Once installed, obtain the required
dependencies:

```
cd acceptance-tests
bundle install
```

Make sure the server is started on port 5000 (see [Running the Server](#running-the-server)). Once the server is
running, execute the acceptance test suite:

```
bundle exec spinach
```

To run a single feature test, filter the test by the relevant feature tag, e.g.:

```
bundle exec spinach -t @simple-get
```

#### Features

The following feature tags are available:

1. @simple-get
1. @simple-head
1. @simple-options
1. @simple-post
1. @simple-redirect
1. @not-allowed
1. @not-found
