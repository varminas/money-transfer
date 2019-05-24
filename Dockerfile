FROM open-liberty

USER root
# Symlink servers directory for easier mounts.
RUN ln -s /opt/ol/wlp/usr/servers /servers

# Copy the JDBC driver
COPY backendServices/target/liberty/wlp/usr/shared /opt/ol/wlp/usr/shared

USER 1001

# Run the server script and start the defaultServer by default.
ENTRYPOINT ["/opt/ol/wlp/bin/server", "run"]
CMD ["defaultServer"]
