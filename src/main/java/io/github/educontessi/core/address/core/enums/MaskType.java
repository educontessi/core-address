package io.github.educontessi.core.address.core.enums;

/**
 * Class used to show what type of mask to use
 *
 * @author Eduardo Possamai Contessi
 */
public enum MaskType {

    CPF("###.###.###-##"), CNPJ("##.###.###/####-##"), CEP("#####-###");

    private final String mask;

    MaskType(String mask) {
        this.mask = mask;
    }

    public String getMask() {
        return mask;
    }

    public static MaskType getPersonMaskType(PersonType personType) {
        return personType.equals(PersonType.PHYSICAL_PERSON) ? MaskType.CPF : MaskType.CNPJ;
    }

}
