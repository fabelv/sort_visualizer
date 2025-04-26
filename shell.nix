{ pkgs ? import <nixpkgs> { }, name ? "default-java-project" }:

pkgs.mkShell {
  buildInputs = [
    # java
    pkgs.jdk23
    pkgs.gradle
    pkgs.jdt-language-server
    pkgs.vscode-extensions.vscjava.vscode-java-debug
    pkgs.vscode-extensions.vscjava.vscode-java-test

    # javafx
    pkgs.libglvnd
    pkgs.xorg.libXxf86vm
    pkgs.glib.out
    pkgs.gtk3
    pkgs.xorg.libXtst
  ];

  shellHook = ''
    export JAVA_HOME="${pkgs.jdk23}"
    export JDTLS_HOME="${pkgs.jdt-language-server}"
    export JAVA_DEBUG_PATH="${pkgs.vscode-extensions.vscjava.vscode-java-debug}"
    export JAVA_TEST_PATH="${pkgs.vscode-extensions.vscjava.vscode-java-test}"

    export JDTLS_WORKSPACE="$HOME/.local/share/nvim/jdtls-workspace/${name}"
    export JDTLS_WRITABLE_CONFIG="$HOME/.local/share/nvim/${name}-jdtls-config"

    mkdir -p "$JDTLS_WORKSPACE"
    mkdir -p "$JDTLS_WRITABLE_CONFIG"

    mkdir -p "$JDTLS_WRITABLE_CONFIG"
    if [ -z "$(ls -A "$JDTLS_WRITABLE_CONFIG")" ]; then
      cp -r "$JDTLS_HOME/share/java/jdtls/config_linux/"* "$JDTLS_WRITABLE_CONFIG/"
    fi

    # make native libraries available to JavaFX
    export LD_LIBRARY_PATH="${pkgs.libglvnd}/lib:${pkgs.gtk3}/lib:${pkgs.glib.out}/lib:${pkgs.xorg.libXxf86vm}/lib:${pkgs.xorg.libXtst}/lib:$LD_LIBRARY_PATH"
  '';
}



