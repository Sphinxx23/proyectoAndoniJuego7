
package dao;

public interface IDAOJugadorPostgre<T> {
    public abstract void alta(T o);
    public abstract void modificacion (T nuevo);
    public abstract void baja (T viejo);
    public abstract void consulta (T o); 
    public abstract void listaNombre ();
    public abstract void listaExperiencia ();
    public abstract void top10xp ();
   
}
