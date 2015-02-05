package com.tannerperrien.gis;

final class Modules {

    private Modules() {

    }

    static Object[] list(GISApplication app) {
        return new Object[] {
            new AppModule(app),
        };
    }
}
