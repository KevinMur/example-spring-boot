FROM java:8-jre

MAINTAINER ${project.organization.name} <${project.organization.url}>

LABEL maven.project.groupId=${project.groupId} \
      maven.project.artifactId=${project.artifactId} \
      maven.project.version=${project.version}

ENV "PATH"="${application.root}/bin:${PATH}" \
    "RUN_JAVA_OPTS"="-D${project.groupId}=${project.artifactId}=${project.version}" \
    "SPRING_CONFIG_LOCATION="${application.root}/etc/"

WORKDIR ${application.root}

COPY maven/ ${application.root}/

RUN set -x \
 && bash -c 'mkdir -vp ${application.root}/{bin,etc,lib,log,tmp}' \
 && ln -vs ${application.root}/etc ${application.root}/config \
 && find ${application.root} -type f -name "*.jar" -exec touch -c {} \; \
 && find ${application.root}/bin -type f -exec chmod -v +x {} \;

ENTRYPOINT ["run-java", "-Djava.io.tmpdir=${application.root}/tmp", "-Djava.security.egd=file:/dev/./urandom", "-jar", "${application.root}/${project.build.finalName}.${project.packaging}"]
