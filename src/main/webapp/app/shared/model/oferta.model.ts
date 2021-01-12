export interface IOferta {
  id?: number;
  descripcion?: string;
  precioHabitual?: number;
  precioRebajado?: number;
  link?: string;
}

export class Oferta implements IOferta {
  constructor(
    public id?: number,
    public descripcion?: string,
    public precioHabitual?: number,
    public precioRebajado?: number,
    public link?: string
  ) {}
}
