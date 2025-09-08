#!/usr/bin/env python3
"""
converter_kettle_xml2txt.py
Converte .ktr, .kjb e .xml em .txt dentro da subpasta 'txt'.
"""

import argparse
from pathlib import Path
import shutil
import sys

# extensões que serão tratadas
EXTENSOES = {".ktr", ".kjb", ".xml"}

def processar(origem: Path, destino_dir: Path) -> None:
    """Copia o conteúdo de 'origem' para '<destino_dir>/<nome>.txt'."""
    destino = destino_dir / f"{origem.stem}.txt"
    shutil.copyfile(origem, destino)
    print(f"[OK] {destino.relative_to(destino_dir.parent)}")

def converter(base_dir: Path, recursive: bool) -> None:
    """Percorre a pasta base_dir e converte arquivos das EXTENSOES."""
    out_dir = base_dir / "txt"
    out_dir.mkdir(parents=True, exist_ok=True)

    padrao = "**/*" if recursive else "*"

    for file in base_dir.glob(padrao):
        if file.is_file() and file.suffix.lower() in EXTENSOES:
            processar(file, out_dir)

def main() -> None:
    parser = argparse.ArgumentParser(
        description="Converte .ktr, .kjb e .xml em .txt (subpasta 'txt')."
    )
    parser.add_argument("pasta", type=Path,
                        help="Diretório onde estão os arquivos")
    parser.add_argument("-r", "--recursive", action="store_true",
                        help="Buscar arquivos também em subpastas")
    args = parser.parse_args()

    if not args.pasta.is_dir():
        sys.exit("Erro: diretório de entrada não existe.")

    converter(args.pasta, recursive=args.recursive)

if __name__ == "__main__":
    main()
