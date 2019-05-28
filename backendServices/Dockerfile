FROM open-liberty

USER 0

# Symlink servers directory for easier mounts.
RUN ln -s /opt/ol/wlp/usr/servers /servers

#read/write access to non-root user 1001
RUN chown -R 1001:0 /config && \
    chown -R 1001:0 /output && \
    chown -R 1001:0 /logs && \
    chown -R 1001:0 /opt/ol && \
    chmod -R g+rw /config && \
    chmod -R g+rw /output && \
    chmod -R g+rw /logs

ENV RANDFILE=/tmp/.rnd \
    JVM_ARGS="-Xshareclasses:name=liberty,nonfatal,cacheDir=/output/.classCache/"

#copy over the bootstrap properties
COPY target/liberty/wlp/usr/servers/defaultServer/bootstrap.properties /config

#copy over the server.env properties
COPY target/liberty/wlp/usr/servers/defaultServer/server.env /config

#copy over the server.xml
COPY src/main/liberty/config/server.xml /config

COPY target/liberty/wlp/usr/servers/defaultServer/resources /config/resources

#copy the artifact
COPY target/money-transfer.war /config/apps/money-transfer.war

# Copy the JDBC driver
COPY target/liberty/wlp/usr/shared /opt/ol/wlp/usr/shared

RUN chown -R 1001:0 /config \
    && chown -R 1001:0 /opt/ol/wlp/usr/shared \
    && chown -R 1001:0 /opt/ol

EXPOSE 5050 5051

USER 1001

# Run the server script and start the defaultServer by default.
ENTRYPOINT ["/opt/ol/wlp/bin/server", "run"]
CMD ["defaultServer"]
