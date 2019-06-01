package lt.arminai.moneyTransfer.health;

import javax.enterprise.context.ApplicationScoped;

import lt.arminai.moneyTransfer.controller.UserResource;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class SystemHealth implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        if (!System.getProperty("wlp.server.name").equals("defaultServer")) {
            return HealthCheckResponse.named(UserResource.class.getSimpleName())
                    .withData("default server", "not available").down()
                    .build();
        }
        return HealthCheckResponse.named(UserResource.class.getSimpleName())
                .withData("default server", "available").up().build();
    }
}
