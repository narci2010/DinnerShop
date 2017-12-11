package com.export.interfaces;

public interface Exportable<T extends Exporter> {
    String export(T exporter);
}
