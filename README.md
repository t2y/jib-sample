# jib-sample

jib sample code

## How to build

To build to a Docker daemon, like this.

```bash
$ ./gradlew jibDockerBuild
```

Confirm the target image was built.

```bash
$ docker images | grep jib-sample
ghcr.io/t2y/jib-sample    latest    acf91de5b3f5   52 years ago    151MB
```

Confirm the docker image information.

```bash
$ docker inspect ghcr.io/t2y/jib-sample
```

## How to run

```bash
$ docker run --rm ghcr.io/t2y/jib-sample a b c
Hello world
args[0]: a
args[1]: b
args[2]: c
```

## How to publish

Get git revision from jar manifest.

```bash
$ ./gradlew clean jar
$ labels_=$(java -jar build/libs/jib-sample.jar | jq --raw-output '.[] | "version.\(.artifactId)=\(.revision)"' | tr -s "\n" ",")
$ LABELS=${labels_::-1}
$ echo $LABELS
version.jib-sample=478304ac
```

To publish to a Github Packages, like this.

```bash
$ echo $CR_PAT | docker login ghcr.io -u $USERNAME --password-stdin
$ ./gradlew jib -Djib.container.labels="${LABELS}"
```

Confirm a docker image has the git revision label.

```bash
$ docker pull ghcr.io/t2y/jib-sample:latest
$ docker inspect ghcr.io/t2y/jib-sample:latest | jq '.[].Config.Labels'
{
  "version.jib-sample": "478304ac"
}
```

