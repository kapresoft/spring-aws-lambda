#!/usr/bin/env bash
# run in project directory
jar_file=spring-lambda-0.0.1.jar
build_cmd="./mvnw package -q -P shade,skip-checks"
profiles=-Dspring.profiles.active=dev
run_cmd="java ${profiles} -cp target/${jar_file} com.kapresoft.demo.springbootlambda.LambdaRunner"

function _build() {
    echo "Building: ${build_cmd}"
    eval ${build_cmd}
}

function _run() {
    eval ${run_cmd} && echo "Executed: ${run_cmd}"
}

#eval ${run_cmd}
_build && _run
