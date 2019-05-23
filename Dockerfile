FROM open-liberty

USER root
# Symlink servers directory for easier mounts.
RUN ln -s /opt/ol/wlp/usr/servers /servers
#RUN ln -s /opt/ol/wlp/usr/shared /shared
#RUN mkdir -p /opt/ol/wlp/output/defaultServer
#RUN chmod -R 777 /opt/ol/wlp/output/defaultServer
USER 1001

# Run the server script and start the defaultServer by default.
ENTRYPOINT ["/opt/ol/wlp/bin/server", "run"]
CMD ["defaultServer"]
