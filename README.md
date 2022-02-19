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

## How to publish

To publish to a Github Packages, like this.

```bash
$ echo $CR_PAT | docker login ghcr.io -u USERNAME --password-stdin
$ ./gradlew jib
```
