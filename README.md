# Java Dev Environment Template (with JDTLS + Gradle + Nix)

This flake provides a **Java development shell** optimized for use with **Neovim + JDTLS + DAP**, with zero setup friction once inside the shell.

---

## What's Inside

### `shell.nix` automatically sets up:

- Java 23 (via `jdk23`)
- Gradle (via `gradle`)
- JDTLS (Eclipse Java Language Server)
- Java Debug Adapter (`vscode-java-debug`)
- Java Test Adapter (`vscode-java-test`)

And sets these env vars for Neovim integration:

```bash
JAVA_HOME
JDTLS_HOME
JAVA_DEBUG_PATH
JAVA_TEST_PATH
JDTLS_WORKSPACE
JDTLS_WRITABLE_CONFIG
```

`shell.nix` also:
- Ensures `JDTLS_WORKSPACE` and `JDTLS_WRITABLE_CONFIG` directories exist
- Initializes the writable config directory (JDTLS_WRITABLE_CONFIG) on first use by copying from the read-only JDTLS config shipped in the Nix store
This is required because the Eclipse language server expects to write to its configuration directory — but packages in the Nix store are immutable (read-only). So we copy the config to a writable location (~/.local/share/nvim/...) before using it.

---

## Neovim Configuration

> These configs are not part of this flake, but are essential for full functionality.

### Debugging (`nvim-dap` + `nvim-dap-ui`)

[My config](https://github.com/fabelv/nix-config/blob/main/home-manager/features/editors/nvim/plugin/debug.lua)

Notes:
- No need to define `dap.adapters.java` manually — `nvim-jdtls` handles this
- No need to define `dap.configurations.java` either
- Just run `:JdtUpdateDebugConfigs` or `:lua require('jdtls.dap').setup_dap_main_class_configs()` after JDTLS finishes initializing
- Debug test classes or main classes directly from Neovim!

---

### Java (JDTLS) integration

[My config](https://github.com/fabelv/nix-config/blob/main/home-manager/features/editors/nvim/plugin/java.lua)

Highlights:
- `jdtls.start_or_attach(config)` is triggered automatically in a `FileType` autocmd
- Includes setup for:
  - Workspace dir
  - Writable config
  - Runtime (JavaSE-21)
  - Gradle auto-import
  - Debug + test bundles (using env vars from `shell.nix`)
  - Which-key keybindings for LSP & DAP Java actions
- Designed to only load JDTLS when editing Java files (no global overhead)

---

## Getting Started

```bash
nix develop
gradle init # if starting a fresh project
```

Once JDTLS starts and finishes loading the project:

```vim
:JdtUpdateDebugConfigs
:lua require('dap').continue()
```

---

Made by [fabelv](https://github.com/fabelv)
