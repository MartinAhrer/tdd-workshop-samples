#!/usr/bin/env bash
set -euo pipefail

: "${BUILD_DIR:="build/"}"


function writeDiff() {
  echo "include::${2}[]" >> ${1}
}

function openSourceBlock() {
  {
    echo "== $(git --no-pager log --format=%B -n 1 ${2})"
    echo "Commit ${2}"

    echo "[source,java]"
    echo "----"
  } >> ${1}
}

function closeSourceBlock() {
  echo "----" >> ${1}
}


function writeCheckoutScript() {
  {
    echo "pushd money-tdd-by-example-kent-beck"
    echo "git checkout ${2}"
    echo "popd"
  } > "${1}"
  chmod +x "${1}"
}

{
  echo "= Kent Beck Money Example commit by commit transcript"
  echo ":toc: left"
} > "${BUILD_DIR}patches.adoc"


pushd money-tdd-by-example-kent-beck
mkdir -p ${BUILD_DIR}patch/
i=0
for commit in $(git rev-list --topo-order HEAD); do
  if [ $commit == "1aa4987b5d4ae65a173fd9df09e1cd7f3e92a0e7" ]; then
    break
  fi

  echo "Processing commit ${commit}"
  ((i++))
  git --no-pager diff $commit^ $commit --output "${BUILD_DIR}patch/${i}-${commit}.patch"

  openSourceBlock "${BUILD_DIR}patches.adoc" $commit
  writeDiff "${BUILD_DIR}patches.adoc" "patch/${i}-${commit}.patch"
  closeSourceBlock "${BUILD_DIR}patches.adoc"

  writeCheckoutScript "${BUILD_DIR}${i}.sh" "${commit}"
done

popd