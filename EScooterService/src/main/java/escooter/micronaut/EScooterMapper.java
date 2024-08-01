package escooter.micronaut;



public class EScooterMapper implements Mapper<EScooter, EScooterInfo> {

    @Override
    public EScooterInfo convert(final EScooter e) {
        return new EScooterInfo(e.getId(), e.getState(), e.getLocation().orElse(null));
    }
    @Override
    public EScooter revert(final EScooterInfo e) {
        final var es = new EScooter(e.getState(), e.getLocation().orElse(null));
        es.setId(e.getId());
        return es;
    }

}
